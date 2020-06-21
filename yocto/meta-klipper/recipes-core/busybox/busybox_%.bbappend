FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "\
    file://runit.cfg \
    file://runit.init \
    "

PACKAGES =+ "${PN}-runit"
INITSCRIPT_PACKAGES += "${PN}-runit"
FILES_${PN}-runit = "${sysconfdir}/init.d/runit"

INITSCRIPT_NAME_${PN}-runit = "runit"
INITSCRIPT_PARAMS_${PN}-runit = "defaults 80"

do_install_append() {
    install -d ${D}${localstatedir}/service
    install -d ${D}${sysconfdir}/init.d
    install ${WORKDIR}/runit.init ${D}${sysconfdir}/init.d/runit
}
