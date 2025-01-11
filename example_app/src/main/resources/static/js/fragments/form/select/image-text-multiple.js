document.addEventListener("alpine:init", () => {
    const selectUtility = new window.GlobalImageTextSelectUtility();

    document.querySelectorAll(".image-text-select.multiple").forEach((selectTag) => {
        const selectName = selectTag.name;

        const select = new SlimSelect({
            select: selectTag,
            settings: {
                showSearch: selectTag.dataset.searchable === "true",
                hideSelected: true,
                openPosition: "up",
                closeOnSelect: false,
            },
            events: {
                afterChange: () => {
                    select.getData().forEach((data) => {
                        const optionContainer = selectUtility.getOptionTagByData(selectName, data);

                        if (optionContainer == null) {
                            return;
                        }
                        if (data.selected) {
                            optionContainer.classList.remove("hidden");
                            optionContainer.style.height = optionContainer.offsetHeight + "px";

                            return;
                        }

                        optionContainer.classList.add("hidden");
                    });

                    setAddButtonState(select, selectName);
                },
            },
        });

        select.setData(selectUtility.setSelectData(select, selectName));

        if (selectTag.disabled) {
            return;
        }

        select.open();
        select.close();
        setMultiSelectRemoveListener(select, selectName);
        addButtonListener(select, selectName);
    });

    function addButtonListener(select, selectName) {
        const button = selectUtility.getAddButton(selectName);

        setAddButtonState(select, selectName);

        button.addEventListener("click", (e) => {
            select.open();
            e.preventDefault();
        });
    }

    function setAddButtonState(select, selectName) {
        const button = selectUtility.getAddButton(selectName);
        const disabled = select.getSelected().length === select.getData().length - 1;

        button.disabled = disabled;

        if (disabled) {
            select.close();
        }
    }

    function setMultiSelectRemoveListener(select, selectName) {
        selectUtility
            .getContainerTagById(selectName)
            .querySelectorAll(".remove-container")
            .forEach((buttonContainer) => {
                buttonContainer.classList.remove("hidden");
                buttonContainer.classList.add("active");

                const removeClass = "remove-item";
                const button = buttonContainer.querySelector("button");
                const optionContainer = selectUtility.getOptionTagById(selectName, button.dataset.removeSelected);

                if (optionContainer.classList.contains("hidden")) {
                    optionContainer.classList.remove("hidden");
                    optionContainer.style.height = optionContainer.offsetHeight + "px";
                    optionContainer.classList.add("hidden");
                } else {
                    optionContainer.style.height = optionContainer.offsetHeight + "px";
                }

                optionContainer.addEventListener("transitionend", (event) => {
                    if (event.propertyName !== "opacity" || !optionContainer.classList.contains(removeClass)) {
                        return;
                    }

                    select.setSelected(select.getSelected().filter((data) => data !== button.dataset.removeSelected));
                    document.querySelectorAll(".remove-container.active").forEach((container) => {
                        container.classList.remove("hidden");
                    });
                    optionContainer.classList.remove(removeClass);
                });

                button.addEventListener("click", () => {
                    document.querySelectorAll(".remove-container.active").forEach((container) => {
                        container.classList.add("hidden");
                    });
                    optionContainer.classList.add(removeClass);
                });
            });
    }
});
