document.addEventListener("alpine:init", () => {
    const selectUtility = new window.GlobalImageTextSelectUtility();
    document.querySelectorAll(".image-text-select.single").forEach((selectTag) => {
        const select = new SlimSelect({
            select: selectTag,
            settings: {
                showSearch: selectTag.dataset.searchable === "true",
                hideSelected: true,
            },
        });

        select.setData(selectUtility.setSelectData(select, selectTag.name));
    });
});
