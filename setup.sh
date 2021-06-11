#!/bin/sh

unset BBPATH BBLAYERS BB_ENV_EXTRAWHITE DL_DIR YOCTO_TOPDIR BUILDDIR
unset LD_LIBRARY_PATH

export YOCTO_TOPDIR=`pwd`

# get rid of trailing and leading slashes
TARGET_CONFIG="$(echo $1 | sed -e 's|^/||' -e 's|/$||')"

export BBPATH=${YOCTO_TOPDIR}/${TARGET_CONFIG}
export BUILDDIR=${YOCTO_TOPDIR}/${TARGET_CONFIG}
export DL_DIR=${YOCTO_TOPDIR}/downloads
export BB_ENV_EXTRAWHITE="YOCTO_TOPDIR DL_DIR BUILDDIR PYTHONPATH"

export PATH=${YOCTO_TOPDIR}/yocto/meta-klipper/scripts:${YOCTO_TOPDIR}/yocto/poky/scripts:${YOCTO_TOPDIR}/yocto/poky/bitbake/bin:${PATH}
export PYTHONPATH=${YOCTO_TOPDIR}/yocto/meta-klipper/scripts/lib:${YOCTO_TOPDIR}/yocto/poky/bitbake/lib

if [ ! -d /opt/poky/3.1.2 ]; then
    echo "Please install http://downloads.yoctoproject.org/releases/yocto/yocto-3.1.2/buildtools/x86_64-buildtools-extended-nativesdk-standalone-3.1.2.sh"
else
    source /opt/poky/3.1.2/environment-setup-x86_64-pokysdk-linux
    export PYTHONPATH=$PYTHONPATH:/opt/poky/3.1.2/sysroots/x86_64-pokysdk-linux/usr/lib/python3.8/site-packages/
fi

echo "Setup complete for $TARGET_CONFIG"

cd $TARGET_CONFIG
