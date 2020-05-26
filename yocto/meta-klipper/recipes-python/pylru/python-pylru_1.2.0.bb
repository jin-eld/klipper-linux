SUMMARY = "A least recently used (LRU) cache implementation"
SECTION = "devel/python"

HOMEPAGE = "https://pypi.python.org/pypi/pylru/"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI[sha256sum] = "492f934bb98dc6c8b2370c02c95c65516ddc08c8f64d27f70087eb038621d297"

inherit pypi setuptools

PYPI_PACKAGE = "pylru"
