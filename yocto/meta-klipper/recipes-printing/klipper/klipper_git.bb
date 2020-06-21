DESCRIPTION = "Klipper 3D Printing Software"
HOMEPAGE = "https://www.klipper3d.org/"
LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

inherit setuptools pkgconfig runit-service

# setting "DEPENDS =" will break setuptools, make sure to append
DEPENDS += "libusb"

SRC_URI = "\
    git://github.com/KevinOConnor/klipper.git;protocol=https \
    file://0001-Use-setuptools-for-packaging.patch \
    file://klipper.default \
    file://klipper.run \
    "

SRCREV = "${AUTOREV}"
PV = "0.8.0+gitr${SRCPV}"

S = "${WORKDIR}/git"

RDEPENDS_${PN} = "\
    python \
    python-pyserial \
    python-cffi \
    python-greenlet \
    python-jinja2 \
    udev \
    "

FILES_${PN} += "${sysconfdir} ${localstatedir}"
CONFFILES_${PN} += "${sysconfdir}/klipper/printer.cfg"

RUNIT_SERVICES = "klipper"

do_compile() {
    ${CC} ${CFLAGS} ${LDFLAGS} -o ${B}/hub-ctrl ${S}/lib/hub-ctrl/hub-ctrl.c `pkg-config --cflags --libs libusb`
}

do_install_append() {
    install -m 0755 -d ${D}${sysconfdir}/klipper
    install -m 0755 -d ${D}${sysconfdir}/default
    install -m 0644 ${WORKDIR}/klipper.default ${D}${sysconfdir}/default/klipper
    install -m 0755 -d ${D}${sysconfdir}/runit/klipper
    install -m 0755 ${WORKDIR}/klipper.run ${D}${sysconfdir}/runit/klipper/run
    install -m 0755 -d ${D}${bindir}
    install -m 0755 ${B}/hub-ctrl ${D}${bindir}/hub-ctrl

    install -d ${D}${localstatedir}/lib/klipper/sdcard
    install -d ${D}${localstatedir}/lib/klipper/config
    install -m 0644 ${S}/config/*.cfg ${D}${localstatedir}/lib/klipper/config
}
