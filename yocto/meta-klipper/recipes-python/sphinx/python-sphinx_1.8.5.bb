SUMMARY = "Sphinx"
SECTION = "devel/python"

HOMEPAGE = "https://pypi.org/project/Sphinx/1.8.5/"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=cc3ed00294f08c93200bc064c73c9d40"

PYPI_PACKAGE = "Sphinx"
#PYPI_SRC_URI = "https://files.pythonhosted.org/packages/2a/86/8e1e8400bb6eca5ed960917952600fce90599e1cb0d20ddedd81ba163370/Sphinx-${PV}.tar.gz"

SRC_URI[sha256sum] = "c7658aab75c920288a8cf6f09f244c6cfdae30d82d803ac1634d9f223a80ca08"

inherit pypi setuptools

BBCLASSEXTEND = "native"
