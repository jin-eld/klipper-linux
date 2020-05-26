
SUMMARY = "Python Jinja2: A small but fast and easy to use stand-alone template engine written in pure python."
DESCRIPTION = "Python Jinja2: A small but fast and easy to use stand-alone template engine written in pure python."
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=20c831f91dd3bd486020f672ba2be386"

SRC_URI[sha256sum] = "35341f3a97b46327b3ef1eb624aadea87a535b8f50863036e085e7c426ac5891"

PYPI_PACKAGE = "Jinja2"

inherit pypi setuptools

RDEPENDS_${PN} += "\
    ${PYTHON_PN}-crypt \
    ${PYTHON_PN}-io \
    ${PYTHON_PN}-json \
    ${PYTHON_PN}-lang \
    ${PYTHON_PN}-markupsafe \
    ${PYTHON_PN}-math \
    ${PYTHON_PN}-netclient \
    ${PYTHON_PN}-pickle \
    ${PYTHON_PN}-pprint \
    ${PYTHON_PN}-re \
    ${PYTHON_PN}-shell \
    ${PYTHON_PN}-textutils \
    ${PYTHON_PN}-threading \
    ${PYTHON_PN}-numbers \
"

CLEANBROKEN = "1"

BBCLASSEXTEND = "native nativesdk"
