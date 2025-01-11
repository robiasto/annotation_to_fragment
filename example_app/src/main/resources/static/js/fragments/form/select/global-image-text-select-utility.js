class GlobalImageTextSelectUtility {
    constructor() {}

    getPlaceholderText(selectTag) {
        const placeholderOption = selectTag.querySelector("[data-placeholder-text]");

        return placeholderOption == null ? "" : placeholderOption.dataset.placeholdeText;
    }

    getContainerTagById(selectName) {
        const id = "option-container-" + selectName;
        const tag = document.getElementById(id);

        if (!tag) {
            throw new Error("Container tag not found for id: #" + id);
        }

        return tag;
    }

    getOptionTagById(selectName, optionId) {
        const id = "option-" + selectName + "-" + optionId;
        const tag = document.getElementById(id);

        if (!tag) {
            throw new Error("Option tag not found for id: #" + id);
        }

        return tag;
    }

    getOptionTagByData(selectName, data) {
        if (data.data.placeholderText) {
            return;
        }

        return this.getOptionTagById(selectName, data.value);
    }

    getAddButton(selectName) {
        const id = "add-button-" + selectName;
        const tag = document.getElementById(id);

        if (!tag) {
            throw new Error("Option AddButton tag not found for id: #" + id);
        }

        return tag;
    }

    setSelectData(select, selectName) {
        const selectData = select.getData();

        selectData.forEach((data) => {
            const optionContainer = this.getOptionTagByData(selectName, data);
            if (optionContainer == null) {
                return;
            }

            data.html = optionContainer.innerHTML;
        });

        return selectData;
    }
}

window.GlobalImageTextSelectUtility = GlobalImageTextSelectUtility;
