SUMMARY = "A collection of cache libraries in the same API interface. Extracted from werkzeug."
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a32a7594742a04098377c0c777ec1af7"

SRC_URI[sha256sum] = "8b889b509d372095357b8705966e1282d40835c4126d7c2b07fd414514d8ae8d"

inherit pypi setuptools

PYPI_PACKAGE = "cachelib"

BBCLASSEXTEND = "native nativesdk"
