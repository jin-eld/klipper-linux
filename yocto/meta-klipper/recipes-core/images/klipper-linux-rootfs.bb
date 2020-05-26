include recipes-core/images/core-image-minimal.bb

DESCRIPTION = "Klipper Linux rootfs image"
IMAGE_FEATURES_append = " package-management ssh-server-dropbear"
IMAGE_INSTALL_append = " packagegroup-klipper-image"

IMAGE_FSTYPES = "ext4 ext4.gz"

IMAGE_BASENAME = "${PN}"

IMAGE_ROOTFS_EXTRA_SPACE = "204800"

inherit extrausers
EXTRA_USERS_PARAMS = "\
    useradd -p \$6\$75uXvknClpLLmi\$SaSiPB9qALjsPn3W43Kn7rtAcW9Gz/fqKJYzAiGBestRPR8t8NyBUvC8OO49T61usTCQZgkDqBfh3GGApWuLP0 printer ; \
    usermod -a -G klipper,octoprint printer ; \
    "

create_extlinux_config() {
    mkdir -p ${IMAGE_ROOTFS}/boot/extlinux
    cat > ${IMAGE_ROOTFS}/boot/extlinux/extlinux.conf <<EOF
default yocto

label yocto
    kernel /boot/${KERNEL_IMAGETYPE}
    devicetree /boot/${KERNEL_DEVICETREE}
    append console=tty1 console=ttyS2,115200n8 rw root=/dev/${RK_BOOT_DEVICE}p6 rootfstype=ext4 init=/sbin/init
EOF
}

ROOTFS_POSTPROCESS_COMMAND_append = " create_extlinux_config;"

