<#import "macros/head_footer_style.ftlh" as hs>
    <@hs.headStyle>

<main
        class="px-3 text-center text-white bg-dark d-flex align-items-center justify-content-center flex-column view"
>
    <p class="lead text-start fs-1" style="width: 50vw">
        Відшукайте свою наступну зупинку
    </p>
    <form method="get" action="/find" class="d-flex">
        <div class="form-floating" style="width: 350px">
            <input
                    type="text"
                    class="form-control"
                    id="country"
                    name="country"
                    placeholder="Країна"
                    style="border-radius: 0.25rem 0 0 0.25rem;"
            />
            <label class="text-dark" for="country"
            >Країна</label
            >
        </div>
        <div class="form-floating" style="width: 350px">
            <input
                    type="text"
                    class="form-control"
                    id="city"
                    name="city"
                    placeholder="Місто"
                    style="border-radius: 0.25rem 0 0 0.25rem;"
            />
            <label class="text-dark" for="city"
            >Місто</label
            >
        </div>
        <div class="form-floating" style="width: 270px">
            <input
                    type="date"
                    class="form-control no-border-radius"
                    id="date"
                    name="date"
                    placeholder="На яку дату плануєте подорож?"
            />
            <label class="text-dark" for="date"
            >На яку дату плануєте подорож?</label
            >
        </div>
        <div class="form-floating">
            <input
                    type="number"
                    min="1"
                    step="1"
                    class="form-control no-border-radius"
                    id="days"
                    name="days"
                    placeholder="На скільки днів?"
            />
            <label class="text-dark" for="days">На скільки днів?</label>
        </div>
        <div class="form-floating">
            <input
                    type="number"
                    min="1"
                    step="1"
                    class="form-control no-border-radius"
                    id="people"
                    name="people"
                    placeholder="На скільки людей?"
            />
            <label class="text-dark" for="people">На скільки людей?</label>
        </div>
        <div>
            <button class="h-100 fs-4 btn btn-warning" type="submit"
                    style="border-radius: 0 0.25rem 0.25rem 0;">Пошук</button>
        </div>
    </form>
</main>

    </@hs.headStyle>
