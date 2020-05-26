SUMMARY = "A wrapper for subprocess which provides command pipeline functionality."
SECTION = "devel/python"

HOMEPAGE = "https://pypi.python.org/pypi/sarge"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a5b7bcb59dada77822aa4d479a96cc6d"

SRC_URI[sha256sum] = "da8cc90883f8e5ab4af0d746438f608662f5f2a35da2e858517927edefa134b0"

inherit pypi setuptools

PYPI_PACKAGE = "sarge"
