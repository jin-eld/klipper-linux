FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append_cubietruck = " file://cubietruck_nand.cfg"

SPL_ECC_BINARY = "sunxi-spl-with-ecc.bin"
SPL_ECC_BINARYNAME = "${@os.path.basename(d.getVar('SPL_ECC_BINARY'))}"
SPL_ECC_IMAGE = "${SPL_ECC_BINARYNAME}-${MACHINE}-${PV}-${PR}"
SPL_ECC_SYMLINK = "${SPL_ECC_BINARYNAME}-${MACHINE}"

do_install_append_cubietruck() {
    install -m 644 ${B}/spl/${SPL_ECC_BINARY} ${D}/boot/${SPL_ECC_IMAGE}-${type}-${PV}-${PR}
    ln -sf ${SPL_ECC_IMAGE}-${type}-${PV}-${PR} ${D}/boot/${SPL_ECC_BINARYNAME}-${type}
    ln -sf ${SPL_ECC_IMAGE}-${type}-${PV}-${PR} ${D}/boot/${SPL_ECC_BINARYNAME}

}
do_deploy_append_cubietruck() {
    install -m 644 ${B}/spl/${SPL_ECC_BINARY} ${DEPLOYDIR}/${SPL_ECC_IMAGE}
    rm -f ${DEPLOYDIR}/${SPL_ECC_BINARYNAME} ${DEPLOYDIR}/${SPL_ECC_SYMLINK}
    ln -sf ${SPL_ECC_IMAGE} ${DEPLOYDIR}/${SPL_ECC_BINARYNAME}
    ln -sf ${SPL_ECC_IMAGE} ${DEPLOYDIR}/${SPL_ECC_SYMLINK}
}
