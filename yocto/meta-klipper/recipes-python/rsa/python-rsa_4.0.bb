SUMMARY = "Pure-Python RSA implementation"
SECTION = "devel/python"

HOMEPAGE = "https://pypi.python.org/pypi/rsa/"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c403f6882d4f97a9cd927df987d55634"

SRC_URI[sha256sum] = "1a836406405730121ae9823e19c6e806c62bbad73f890574fff50efa4122c487"

inherit pypi setuptools

PYPI_PACKAGE = "rsa"
