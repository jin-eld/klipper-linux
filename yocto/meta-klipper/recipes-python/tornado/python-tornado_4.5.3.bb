DESCRIPTION = "Tornado is an open source version of the scalable, non-blocking web server and tools that power FriendFeed."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

PYPI_PACKGE = "tornado"
#PYPI_SRC_URI = "https://files.pythonhosted.org/packages/e3/7b/e29ab3d51c8df66922fea216e2bddfcb6430fb29620e5165b16a216e0d3c/tornado-${PV}.tar.gz"

SRC_URI[sha256sum] = "6d14e47eab0e15799cf3cdcc86b0b98279da68522caace2bd7ce644287685f0a"

inherit pypi setuptools

RDEPENDS_${PN} += "\
    ${PYTHON_PN}-compression \
    ${PYTHON_PN}-numbers \
    ${PYTHON_PN}-email \
    ${PYTHON_PN}-pkgutil \
    ${PYTHON_PN}-html \
    ${PYTHON_PN}-json \
    ${PYTHON_PN}-certifi \
    ${PYTHON_PN}-threading \
    ${PYTHON_PN}-ctypes \
    ${PYTHON_PN}-backports-abc \
    ${PYTHON_PN}-backports-ssl \
    ${PYTHON_PN}-singledispatch \
    ${PYTHON_PN}-subprocess \
    "

RDEPENDS_${PN}-test += "${PN} ${PYTHON_PN}-unittest"

PACKAGES =+ "\
    ${PN}-test \
"

FILES_${PN}-test = " \
    ${libdir}/${PYTHON_DIR}/site-packages/*/test \
    ${libdir}/${PYTHON_DIR}/site-packages/*/testing.py* \
"

