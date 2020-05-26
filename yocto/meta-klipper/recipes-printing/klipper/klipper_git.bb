DESCRIPTION = "Klipper 3D Printing Software"
HOMEPAGE = "https://www.klipper3d.org/"
LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

inherit setuptools update-rc.d pkgconfig useradd

# setting "DEPENDS =" will break setuptools, make sure to append
DEPENDS += "libusb"

SRC_URI = "\
    git://github.com/KevinOConnor/klipper.git;protocol=https \
    file://0001-Attempt-to-package-klippy-using-setuptools.patch \
    file://0002-Assume-hub-ctrl-is-installed-on-the-system-and-is-in.patch \
    file://0003-Make-sure-to-also-distribute-menu.cfg.patch \
    file://klipper.init \
    file://klipper.default \
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
    "

FILES_${PN} += "${sysconfdir} ${localstatedir}"
CONFFILES_${PN} += "${sysconfdir}/klipper/printer.cfg"

INITSCRIPT_NAME = "klipper"
INITSCRIPT_PARAMS = "start 80 2 3 4 5 . stop 90 2 3 4 5 ."

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM_${PN} = "klipper"
USERADD_PARAM_${PN} = "--system -d ${localstatedir}/lib/klipper/ -g klipper klipper"

do_compile() {
    ${CC} ${CFLAGS} ${LDFLAGS} -o ${B}/hub-ctrl ${S}/lib/hub-ctrl/hub-ctrl.c `pkg-config --cflags --libs libusb`
}

pkg_postinst_ontarget_${PN}_append () {
    chown -R klipper.klipper $D${sysconfdir}/klipper
}

do_install_append() {
    install -m 0755 -d ${D}${sysconfdir}/klipper
    install -m 0755 -d ${D}${sysconfdir}/default
    install -m 0644 ${WORKDIR}/klipper.default ${D}${sysconfdir}/default/klipper
    install -m 0755 -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/klipper.init ${D}${sysconfdir}/init.d/klipper

    install -m 0755 -d ${D}${bindir}
    install -m 0755 ${B}/hub-ctrl ${D}${bindir}/hub-ctrl

    install -d ${D}${localstatedir}/lib/klipper/sdcard
    install -d ${D}${localstatedir}/lib/klipper/config
    install -m 0644 ${S}/config/*.cfg ${D}${localstatedir}/lib/klipper/config
    chmod -R g+rw ${D}${localstatedir}/lib/klipper
}
