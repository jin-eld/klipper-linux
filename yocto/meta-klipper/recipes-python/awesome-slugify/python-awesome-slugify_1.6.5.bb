SUMMARY = "Python flexible slugify function https://pypi.python.org/pypi/awesome-slugify"
SECTION = "devel/python"

HOMEPAGE = "https://github.com/dimka665/awesome-slugify"

LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://setup.py;beginline=22;endline=24;md5=ae3de4b453d37fcbff2ca5a8a4725cfd"

PYPI_PACKAGE = "awesome-slugify"
#PYPI_SRC_URI = "https://files.pythonhosted.org/packages/34/39/79ef4e640c3651b40de7812f5fcd04698abf14de4f57a81e12b6c753d168/awesome-slugify-${PV}.tar.gz"

SRC_URI[sha256sum] = "bbdec3fa2187917473a2efad092b57f7125a55f841a7cf6a1773178d32ccfd71"

inherit pypi setuptools

RDEPENDS_${PN} = "python-nose \
                  python-regex \
                  python-unidecode \
                 "

BBCLASSEXTEND = "native"
