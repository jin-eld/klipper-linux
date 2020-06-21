# original recipe imported from
# https://git.yoctoproject.org/cgit/cgit.cgi/meta-maker/tree/recipes-webui/octoprint/octoprint_git.bb

SUMMARY = "OctoPrint provides a responsive web interface for controlling a 3D printer."
HOMEPAGE = "http://octoprint.org"

SECTION = "devel/python"

LICENSE = "AGPL-3.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=73f1eb20517c55bf9493b7dd6e480788"

SRC_URI = "\
    git://github.com/OctoPrint/OctoPrint.git;protocol=https;tag=${PV} \ 
    file://config.yaml \
    file://octoprint.run \        
    "

S = "${WORKDIR}/git"

inherit setuptools runit-service


export BUILD_SYS
export HOST_SYS
export STAGING_INCDIR
export STAGING_LIBDIR

BBCLASSEXTEND = "native"

RUNIT_SERVICES = "octoprint"

do_install_append(){
    install -d ${D}${sysconfdir}/octoprint
    install -m 0644 ${WORKDIR}/config.yaml ${D}${sysconfdir}/octoprint/config.yaml
    install -m 0755 -d ${D}${sysconfdir}/runit/octoprint
    install -m 0755 ${WORKDIR}/octoprint.run ${D}${sysconfdir}/runit/octoprint/run
    install -d ${D}${localstatedir}/lib/octoprint
}


FILES_${PN} += "${sysconfdir} ${localstatedir}"
CONFFILES_${PN} += "${sysconfdir}/octoprint/config.yaml"

DEPENDS = "${PYTHON_PN} ${PYTHON_PN}-markdown-native"

INSANE_SKIP_${PN} += "build-deps"

RDEPENDS_${PN} = "\
    ${PYTHON_PN}-markdown \
    ${PYTHON_PN}-flask \
    ${PYTHON_PN}-jinja2 \
    ${PYTHON_PN}-tornado \
    ${PYTHON_PN}-regex \
    ${PYTHON_PN}-flask-login \
    ${PYTHON_PN}-flask-babel \
    ${PYTHON_PN}-flask-assets \
    ${PYTHON_PN}-blinker \
    ${PYTHON_PN}-werkzeug \ 
    ${PYTHON_PN}-werkzeug-tests \ 
    ${PYTHON_PN}-cachelib \
    ${PYTHON_PN}-pyyaml \
    ${PYTHON_PN}-markdown \
    ${PYTHON_PN}-pyserial \
    ${PYTHON_PN}-netaddr \
    ${PYTHON_PN}-watchdog \
    ${PYTHON_PN}-sarge \
    ${PYTHON_PN}-netifaces \
    ${PYTHON_PN}-pylru \
    ${PYTHON_PN}-rsa \
    ${PYTHON_PN}-pkginfo \
    ${PYTHON_PN}-requests \
    ${PYTHON_PN}-semantic-version \
    ${PYTHON_PN}-psutil \
    ${PYTHON_PN}-click \
    ${PYTHON_PN}-awesome-slugify \
    ${PYTHON_PN}-feedparser \
    ${PYTHON_PN}-future \
    ${PYTHON_PN}-websocket-client \
    ${PYTHON_PN}-wrapt \
    ${PYTHON_PN}-emoji \
    ${PYTHON_PN}-frozendict \
    ${PYTHON_PN}-sentry-sdk \
    ${PYTHON_PN}-filetype \
    ${PYTHON_PN}-futures \ 
    ${PYTHON_PN}-monotonic \
    ${PYTHON_PN}-scandir \ 
    ${PYTHON_PN}-chainmap \
    ${PYTHON_PN}-typing \
    ${PYTHON_PN}-pip \
    "

BBCLASSEXTEND = "native"
