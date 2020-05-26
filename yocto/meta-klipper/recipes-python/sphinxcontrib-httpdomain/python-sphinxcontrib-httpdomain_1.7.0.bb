SUMMARY = "Sphinx domain for HTTP APIs."
SECTION = "devel/python"

HOMEPAGE = "https://pypi.python.org/pypi/sphinxcontrib-httpdomain"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=667c3e266c41ac5129a4478ad682b1c3"

PYPI_PACKAGE = "sphinxcontrib-httpdomain"
#PYPI_SRC_URI = "https://pypi.python.org/packages/source/s/sphinxcontrib-httpdomain/sphinxcontrib-httpdomain-${PV}.tar.gz"

SRC_URI[sha256sum] = "ac40b4fba58c76b073b03931c7b8ead611066a6aebccafb34dc19694f4eb6335"

inherit pypi setuptools

BBCLASSEXTEND = "native"
