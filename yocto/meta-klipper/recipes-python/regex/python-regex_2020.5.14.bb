SUMMARY = "Alternative regular expression module, to replace re."
SECTION = "devel/python"

HOMEPAGE = "https://pypi.python.org/pypi/regex"

LICENSE = "PSFL"
LIC_FILES_CHKSUM = "file://setup.py;beginline=32;endline=31;md5=d41d8cd98f00b204e9800998ecf8427e"

PIPY_PACKAGE = "regex"
#PYPI_SRC_URI = "https://pypi.python.org/packages/source/r/regex/regex-${PV}.tar.gz"

SRC_URI[sha256sum] = "ce450ffbfec93821ab1fea94779a8440e10cf63819be6e176eb1973a6017aff5"

inherit pypi setuptools

BBCLASSEXTEND = "native"
