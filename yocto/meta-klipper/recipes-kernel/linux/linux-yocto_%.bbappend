FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "\
    file://usbserial_ch341.cfg \
    file://usbannounce.cfg \
    "
