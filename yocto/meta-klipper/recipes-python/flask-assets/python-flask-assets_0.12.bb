SUMMARY = "Flask webassets integration."
SECTION = "devel/python"

HOMEPAGE = "http://flask-assets.readthedocs.org/en/latest/"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2c0c455026ee819a9365678bea8b2c82"

DEPENDS = "python-webassets"

PYPI_PACKAGE = "Flask-Assets"
#PYPI_SRC_URI = "https://files.pythonhosted.org/packages/86/ff/6000451570745d7a90847f6528d96d6b24c800eaaf9f26cf398accd8cee5/Flask-Assets-${PV}.tar.gz"

SRC_URI[sha256sum] = "6031527b89fb3509d1581d932affa5a79dd348cfffb58d0aef99a43461d47847"

inherit pypi setuptools

RDEPENDS_${PN} = "python-webassets"

BBCLASSEXTEND = "native"
