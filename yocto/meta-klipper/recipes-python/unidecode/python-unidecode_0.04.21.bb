SUMMARY = "ASCII transliterations of Unicode text"
SECTION = "devel/python"

HOMEPAGE = "https://pypi.python.org/pypi/Unidecode"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PYPI_PACKAGE = "Unidecode"

SRC_URI[sha256sum] = "280a6ab88e1f2eb5af79edff450021a0d3f0448952847cd79677e55e58bad051"

inherit pypi setuptools

BBCLASSEXTEND = "native"
