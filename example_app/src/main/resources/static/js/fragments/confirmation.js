document.addEventListener("alpine:init", () => {
    Alpine.data("confirmation", () => ({
        show: false,

        event: null,

        isConfirmed: false,

        isConfirmation() {
            return this.show === true;
        },

        cancel() {
            this.show = false;
        },

        confirmed() {
            this.isConfirmed = true;
            this.event.target.click();
        },

        confirm($event) {
            if (!this.isConfirmed) {
                this.event = $event;
                this.show = true;

                $event.preventDefault();
            }
        },
    }));
});
