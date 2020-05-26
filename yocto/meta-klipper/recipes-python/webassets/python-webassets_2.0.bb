SUMMARY = "Media asset management for Python, with glue code for various web frameworks"
SECTION = "devel/python"

HOMEPAGE = "https://pypi.python.org/pypi/webassets/"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=01c7e9175fd063ebb0a6304af80e9874"

PYPI_PACKGE = "webassets"

SRC_URI[sha256sum] = "167132337677c8cedc9705090f6d48da3fb262c8e0b2773b29f3352f050181cd"

inherit pypi setuptools

BBCLASSEXTEND = "native"
