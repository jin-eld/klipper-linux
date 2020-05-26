SUMMARY = "A microframework based on Werkzeug, Jinja2 and good intentions"
DESCRIPTION = "\
Flask is a microframework for Python based on Werkzeug, Jinja 2 and good \
intentions. And before you ask: It’s BSD licensed!"
HOMEPAGE = "https://github.com/mitsuhiko/flask/"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=19866b76b054ab30c270c0eb9d7e43d7"

SRC_URI[sha256sum] = "fac2b9d443e49f7e7358a444a3db5950bdd0324674d92ba67f8f1f15f876b14f"

CLEANBROKEN = "1"

PYPI_PACKAGE = "Flask"

inherit pypi setuptools

RDEPENDS_${PN} = "\
    ${PYTHON_PN}-click \
    ${PYTHON_PN}-itsdangerous \
    ${PYTHON_PN}-jinja2 \
    ${PYTHON_PN}-werkzeug \
    "
