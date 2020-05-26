SUMMARY = "Simple powerful teting with python"
HOMEPAGE = "http://pytest.org"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=640061b8cee64b308a4d2f9f556c12f2"

SRC_URI += " file://0001-setup.py-remove-setup_requires-for-setuptools-scm.patch"

SRC_URI[sha256sum] = "692d9351353ef709c1126266579edd4fd469dcf6b5f4f583050f72161d6f3592"

inherit update-alternatives pypi setuptools

RDEPENDS_${PN}_class-target += " \
    ${PYTHON_PN}-argparse \
    ${PYTHON_PN}-atomicwrites \
    ${PYTHON_PN}-attrs \
    ${PYTHON_PN}-compiler \
    ${PYTHON_PN}-debugger \
    ${PYTHON_PN}-doctest \
    ${PYTHON_PN}-funcsigs \
    ${PYTHON_PN}-importlib-metadata \
    ${PYTHON_PN}-json \
    ${PYTHON_PN}-logging \
    ${PYTHON_PN}-more-itertools \
    ${PYTHON_PN}-packaging \
    ${PYTHON_PN}-pathlib2 \
    ${PYTHON_PN}-pluggy \
    ${PYTHON_PN}-py \
    ${PYTHON_PN}-setuptools \
    ${PYTHON_PN}-six \
    ${PYTHON_PN}-wcwidth \
"

RDEPENDS_${PN}-ptest += "\
    ${PYTHON_PN}-hypothesis \
"

FILESEXTRAPATHS_prepend := "${THISDIR}/python-pytest:"

ALTERNATIVE_${PN} += "py.test pytest"

NATIVE_LINK_NAME[pytest] = "${bindir}/pytest"
ALTERNATIVE_TARGET[pytest] = "${bindir}/pytest"

ALTERNATIVE_LINK_NAME[py.test] = "${bindir}/py.test"
ALTERNATIVE_TARGET[py.test] = "${bindir}/py.test"

ALTERNATIVE_PRIORITY = "10"

BBCLASSEXTEND = "native nativesdk"
