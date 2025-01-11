document.addEventListener("alpine:init", () => {
    const selectUtility = new window.GlobalImageTextSelectUtility();

    document.querySelectorAll(".plain-select").forEach((selectTag) => {
        new SlimSelect({
            select: selectTag,
            settings: {
                showSearch: selectTag.dataset.searchable === "true",
                placeholderText: selectUtility.getPlaceholderText(selectTag),
            },
        });
    });
});
