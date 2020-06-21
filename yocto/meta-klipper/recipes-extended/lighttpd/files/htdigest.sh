#!/bin/sh
#
# htdigest.sh
# http://redmine.lighttpd.net/projects/lighttpd/wiki/Docs:ModAuth
#
export PATH="/bin:/usr/bin:/usr/sbin:$PATH"

# when input ctrl-c, remove lockfile and exit
trap '[ $lockstart -eq 1 ] && unlock $pfile && exit 0 || exit 0' INT

pfile="/etc/lighttpd.user"
lockstart=0
remove=0

errmsg() {
    echo "$1" 1>&2
}

user_check() {
    check_user=$1
    grep "^${check_user}:" ${pfile} >& /dev/null
    return $?
}

lock() {
    lockfile="$1"
    lockfile="${lockfile}.lock"

    [ -f "${lockfile}" ] && {
        errmsg "WARNING: lock file ${lockfile} is already exists"
        errmsg "         Wait minites for end of previous working ..."
    }

    while [ -f "${lockfile}" ]; do echo >& /dev/null ; done
    touch ${lockfile}
    lockstart=1
}

unlock() {
    lockfile="$1"
    lockfile="${lockfile}.lock"

    [ -f "${lockfile}" ] && rm -f ${lockfile} && lockstart=0
}

usage() {
    errmsg
    errmsg "lightdigest: lighttpd htdigest password generation program"
    errmsg "Scripted by JoungKyun.Kim <http://oops.org>"
    errmsg
    errmsg "Usage: $0 -[hd] -u user -p pass -r realm [-f password_file]"
    errmsg "Options:"
    errmsg "    -h          print this help messages"
    errmsg "    -u user     username"
    errmsg "    -p pass     password"
    errmsg "    -r realm    realm name"
    errmsg "    -f filename password file [default: /etc/lighttpd/conf.d/lighttpd.user]"
    errmsg "    -d          remove user"
    errmsg

    [ $lockstart -eq 1 ] && rm -f ${pfile}.lock

    exit 1
}  

opts=$(getopt df:hp:r:u: $*)
[ $? != 0 ] && usage

set -- ${opts}
for i
do
    case "$i" in
        -d) remove=1; shift;;
        -f) pfile="$2"; shift; shift;;
        -p) pass="$2"; shift; shift;;
        -r) realm="$2"; shift; shift;;
        -u) user="$2"; shift; shift;;
        --) shift; break;
    esac
done

[ -z "$user" ] && errmsg "ERROR: User is none!!" && usage
[ ${remove} -eq 0 -a -z "${realm}" ] && errmsg "ERROR: Realm is none!!" && usage

if [ -z "${pass}" -a ${remove} -eq 0 ]; then
    echo -n "Input new password : "
    read newpass
    echo -n "Reinput password for confirm : "
    read renewpass

    if [ "${newpass}" != "${renewpass}" ]; then
        errmsg "ERROR: Password is not match"
        exit 1
    fi

    pass=${newpass}
fi

lock ${pfile}

if [ ${remove} -eq 0 ]; then
    # User Add Mode
    hash=$(echo -n "${user}:${realm}:${pass}" | md5sum | cut -b -32)
    user_check ${user}
    already=$?

    # [ -f "${pfile}" ] && cp -af ${pfile} ${pfile}.bak
    if [ ${already} -eq 0 ]; then
        # already exists
        sed -i "s/^${user}:.*$/${user}:${realm}:${hash}/g" ${pfile}
    else
        # add new user
        echo "${user}:${realm}:${hash}" >> ${pfile}
    fi
else
    echo "Removing..."
    # User Remove Mode
    tmp_htdigest="/tmp/lighttpd-htdiges.tmp.$$"
    # cp -af ${pfile} ${pfile}.bak
    grep -v "^${user}:" ${pfile} > ${tmp_htdigest}
    mv -f ${tmp_htdigest} ${pfile}
fi

unlock ${pfile}

exit 0
