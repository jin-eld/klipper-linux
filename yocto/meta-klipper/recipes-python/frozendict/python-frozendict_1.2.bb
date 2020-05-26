SUMMARY = "python-frozendict recipe"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${S}/LICENSE.txt;md5=f4da037a49c09b456fdbbc7a5bd36132"

PYPI_PACKAGE = "frozendict"

SRC_URI[sha256sum] = "774179f22db2ef8a106e9c38d4d1f8503864603db08de2e33be5b778230f6e45"

inherit pypi setuptools

