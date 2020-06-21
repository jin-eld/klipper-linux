# recipe imported from Poky
SUMMARY = "Lightweight high-performance web server"
HOMEPAGE = "http://www.lighttpd.net/"
BUGTRACKER = "http://redmine.lighttpd.net/projects/lighttpd/issues"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=e4dac5c6ab169aa212feb5028853a579"

SECTION = "net"

RDEPENDS_${PN} = "\
    lighttpd-module-access \
    lighttpd-module-accesslog \
    lighttpd-module-auth \
    lighttpd-module-authn-file \
    lighttpd-module-cgi \
    lighttpd-module-dirlisting \
    lighttpd-module-indexfile \
    lighttpd-module-openssl \
    lighttpd-module-proxy \
    lighttpd-module-redirect \
    lighttpd-module-setenv \
    lighttpd-module-staticfile \
    create-certificate \
    "

SRC_URI = "http://download.lighttpd.net/lighttpd/releases-1.4.x/lighttpd-${PV}.tar.xz \
        file://lighttpd.conf \
        file://htdigest.sh \
        file://lighttpd.user \
        file://lighttpd.run \
        file://0001-Use-pkg-config-for-pcre-dependency-instead-of-config.patch \
        "

SRC_URI[md5sum] = "be4bda2c28bcbdac6eb941528f6edf03"
SRC_URI[sha256sum] = "6a0b50e9c9d5cc3d9e48592315c25a2d645858f863e1ccd120507a30ce21e927"

PACKAGECONFIG = "openssl pcre zlib"

PACKAGECONFIG[ipv6] = "--enable-ipv6,--disable-ipv6"
PACKAGECONFIG[mmap] = "--enable-mmap,--disable-mmap"
PACKAGECONFIG[libev] = "--with-libev,--without-libev,libev"
PACKAGECONFIG[mysql] = "--with-mysql,--without-mysql,mariadb"
PACKAGECONFIG[ldap] = "--with-ldap,--without-ldap,openldap"
PACKAGECONFIG[attr] = "--with-attr,--without-attr,attr"
PACKAGECONFIG[valgrind] = "--with-valgrind,--without-valgrind,valgrind"
PACKAGECONFIG[openssl] = "--with-openssl,--without-openssl,openssl"
PACKAGECONFIG[krb5] = "--with-krb5,--without-krb5,krb5"
PACKAGECONFIG[pcre] = "--with-pcre,--without-pcre,libpcre"
PACKAGECONFIG[zlib] = "--with-zlib,--without-zlib,zlib"
PACKAGECONFIG[bzip2] = "--with-bzip2,--without-bzip2,bzip2"
PACKAGECONFIG[webdav-props] = "--with-webdav-props,--without-webdav-props,libxml2 sqlite3"
PACKAGECONFIG[webdav-locks] = "--with-webdav-locks,--without-webdav-locks,util-linux"
PACKAGECONFIG[gdbm] = "--with-gdbm,--without-gdbm,gdbm"
PACKAGECONFIG[memcache] = "--with-memcached,--without-memcached,libmemcached"
PACKAGECONFIG[lua] = "--with-lua,--without-lua,lua"

EXTRA_OECONF += "--enable-lfs --without-fam"

inherit autotools pkgconfig gettext runit-service

RUNIT_SERVICES = "lighttpd"
RUNIT_PACKAGES = "lighttpd"

do_install_append() {
    install -d ${D}${sysconfdir}/runit/lighttpd ${D}${sysconfdir}/lighttpd
    install -d ${D}${sysconfdir}/lighttpd.d ${D}/www/pages/
    install -d ${D}${bindir}

    install -m 0755 ${WORKDIR}/lighttpd.run ${D}${sysconfdir}/runit/lighttpd/run
    install -m 0644 ${WORKDIR}/lighttpd.conf ${D}${sysconfdir}/lighttpd

    #For FHS compliance, create symbolic links to /var/log and /var/tmp for logs and temporary data
    ln -sf ${localstatedir}/log ${D}/www/logs
    ln -sf ${localstatedir}/tmp ${D}/www/var

    install -m 0755 -d ${D}${bindir}
    install -m 0755 -d ${D}/www/pages

    install -m 0644 ${WORKDIR}/lighttpd.user ${D}${sysconfdir}/lighttpd/lighttpd.user
    install -m 0755 ${WORKDIR}/htdigest.sh ${D}${bindir}/htdigest.sh

}

FILES_${PN} += "${sysconfdir} /www"

CONFFILES_${PN} = "${sysconfdir}/lighttpd/lighttpd.user"

PACKAGES_DYNAMIC += "^lighttpd-module-.*"

python populate_packages_prepend () {
    lighttpd_libdir = d.expand('${libdir}')
    do_split_packages(d, lighttpd_libdir, r'^mod_(.*)\.so$', 'lighttpd-module-%s', 'Lighttpd module for %s', extra_depends='')
}

pkg_postinst_ontarget_${PN}() {
    #!/bin/sh
    if [ ! -s /etc/lighttpd/dhparam.pem ]; then
        openssl dhparam -out /etc/lighttpd/dhparam.pem 4096
    fi
}
