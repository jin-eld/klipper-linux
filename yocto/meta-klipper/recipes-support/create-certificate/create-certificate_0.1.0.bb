# Script to create a self signed certificate
# Imported from:
# https://git.digitalstrom.org/dss-oe/dss-oe/blob/master/yocto/dS/meta-digitalstrom-devel/recipes-support/create-certificate/create-certificate_0.6.0.bb

DESCRIPTION = "Helper script to create a self signed certificate"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

inherit allarch

RDEPENDS_${PN} = "openssl openssl-misc openssl-bin busybox cronie"

SRC_URI = "file://create-cert \
           file://create-certificate.cron \
"

do_install() {
    install -m 0755 -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/create-cert ${D}${bindir}
    install -m 0755 -d ${D}${sysconfdir}/cron.d/
    install -m 0644 ${WORKDIR}/create-certificate.cron ${D}${sysconfdir}/cron.d/
}

pkg_preinst_${PN} () {
    #!/bin/sh
    if [ "x$D" != "x" ]; then
        exit 0
    fi

    sed -e '/\/usr\/bin\/create-cert/d' -i ${sysconfdir}/crontab
}

pkg_postinst_ontarget_${PN} () {
    #!/bin/sh
    create-cert
}

pkg_prerm_${PN} () {
    #!/bin/sh
    sed -e '/\/usr\/bin\/create-cert/d' -i ${sysconfdir}/crontab
}

