SUMMARY = "Small and dependency free Python package to infer file type and MIME type checking the magic numbers signature of a file or buffer."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=cc0e8af5f14a75ce53b7b9db0f4bd91e"

SRC_URI[sha256sum] = "da393ece8d98b47edf2dd5a85a2c8733e44b769e32c71af4cd96ed8d38d96aa7"

PYPI_PACKAGE = "filetype"

inherit pypi setuptools

BBCLASSEXTEND = "native nativesdk"
