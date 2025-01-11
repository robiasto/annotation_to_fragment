document.addEventListener("alpine:init", () => {
    Alpine.data("topBarMenus", () => ({
        menus: [],
        closeAll() {
            this.menus.forEach((menu) => menu.close());
        },
    }));

    Alpine.data("topBarMenu", () => ({
        show: false,
        open() {
            if (this.show) {
                this.show = false;
                return;
            }

            this.$data.closeAll();
            this.show = true;
        },
        close() {
            this.show = false;
        },
        isVisible() {
            return this.show;
        },
        isHidden() {
            return !this.show;
        },
        init() {
            this.$data.menus.push(this);
        },
    }));
});
