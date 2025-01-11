document.addEventListener("alpine:init", () => {
    Alpine.data("validationFields", (validationUri, form) => ({
        validationUri: validationUri,
        form: form,
        validate($event) {
            if (!$event.target.hasAttribute("required")) {
                return;
            }

            fetch(validationUri + "/" + $event.target.getAttribute("name"), {
                method: "POST",
                body: new FormData(this.form),
            })
                .then((response) => response.json())
                .then((data) => {
                    $event.target.parentElement.querySelector(".validation-errors").innerHTML = data.message;
                });
        },

        validateFieldsAndSubmit() {
            fetch(validationUri, {
                method: "POST",
                body: new FormData(this.form),
            })
                .then((response) => response.json())
                .then((data) => {
                    if (data.length === 0) {
                        form.submit();

                        return;
                    }

                    if (!data.fieldErrorData) {
                        console.error(data);

                        return;
                    }

                    data.forEach((fieldErrorData) => {
                        const fieldError = JSON.parse(fieldErrorData);
                        form
                            .querySelector("[name=" + fieldError.fieldName + "]")
                            .parentElement.querySelector(".validation-errors").innerHTML = fieldError.message;
                    });
                });
        },
    }));
});
