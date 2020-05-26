SUMMARY = "A sophisticated Numeric Processing Package for Python"
SECTION = "devel/python"
LICENSE = "BSD-3-Clause & BSD-2-Clause & PSF & Apache-2.0 & BSD & MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=d26bde5432613cce2334b93985576231"

SRCNAME = "numpy"

SRC_URI = "https://github.com/${SRCNAME}/${SRCNAME}/releases/download/v${PV}/${SRCNAME}-${PV}.tar.gz"

SRC_URI[md5sum] = "5e958c603605f3168b7b29f421f64cdd"
SRC_URI[sha256sum] = "61562ddac78765969959500b0da9c6f9ba7d77eeb12ec3927afae5303df08777"

UPSTREAM_CHECK_URI = "https://github.com/numpy/numpy/releases"
UPSTREAM_CHECK_REGEX = "(?P<pver>\d+(\.\d+)+)\.tar"

# Needed for building with gcc 4.x from centos 7
CFLAGS_append_class-native = " -std=c99"

S = "${WORKDIR}/numpy-${PV}"

CLEANBROKEN = "1"

FILES_${PN}-staticdev += "${PYTHON_SITEPACKAGES_DIR}/numpy/core/lib/*.a"

# install what is needed for numpy.test()
RDEPENDS_${PN} = "${PYTHON_PN}-unittest \
                  ${PYTHON_PN}-difflib \
                  ${PYTHON_PN}-pprint \
                  ${PYTHON_PN}-pickle \
                  ${PYTHON_PN}-shell \
                  ${PYTHON_PN}-nose \
                  ${PYTHON_PN}-doctest \
                  ${PYTHON_PN}-datetime \
                  ${PYTHON_PN}-distutils \
                  ${PYTHON_PN}-misc \
                  ${PYTHON_PN}-mmap \
                  ${PYTHON_PN}-netclient \
                  ${PYTHON_PN}-numbers \
                  ${PYTHON_PN}-pydoc \
                  ${PYTHON_PN}-pkgutil \
                  ${PYTHON_PN}-email \
                  ${PYTHON_PN}-compression \
                  ${PYTHON_PN}-ctypes \
                  ${PYTHON_PN}-threading \
                  ${PYTHON_PN}-multiprocessing \
"

RDEPENDS_${PN}_class-native = ""

BBCLASSEXTEND = "native nativesdk"
