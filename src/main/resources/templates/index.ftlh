<#import "macros/head_footer_style.ftlh" as hs>
    <@hs.headStyle>

<main
        class="px-3 text-center text-white bg-dark d-flex align-items-center justify-content-center flex-column view"
>
    <p class="lead text-start fs-1" style="width: 50vw">
        Відшукайте свою наступну зупинку
    </p>
    <form method="post" action="/find" class="d-flex">
        <div class="form-floating" style="width: 350px">
            <input
                    type="text"
                    class="form-control <#if countryError??>is-invalid</#if>"
                    id="country"
                    name="country"
                    <#if houseFilter?? && houseFilter.country != "%">value="${houseFilter.country}"</#if>
                    placeholder="Країна"
                    style="border-radius: 0.25rem 0 0 0.25rem;"
            />
            <label class="text-dark" for="country">Країна</label>
            <#if countryError??>
                <div class="invalid-feedback">
                    ${countryError}
                </div>
            </#if>
        </div>
        <div class="form-floating" style="width: 350px">
            <input
                    type="text"
                    class="form-control <#if cityError??>is-invalid</#if>"
                    id="city"
                    name="city"
                    <#if houseFilter?? && houseFilter.city != "%">value="${houseFilter.city}"</#if>
                    placeholder="Місто"
                    style="border-radius: 0.25rem 0 0 0.25rem;"
            />
            <label class="text-dark" for="city">Місто</label>
            <#if cityError??>
                <div class="invalid-feedback">
                    ${cityError}
                </div>
            </#if>
        </div>
        <div class="form-floating" style="width: 270px">
            <input
                    type="date"
                    class="form-control no-border-radius <#if dateError??>is-invalid</#if>"
                    id="date"
                    name="date"
                    <#if houseFilter?? && houseFilter.date?? && houseFilter.date != "1970-01-01">value="${houseFilter.date}"</#if>
                    placeholder="На яку дату плануєте подорож?"
            />
            <label class="text-dark" for="date">На яку дату плануєте подорож?</label>
            <#if dateError??>
                <div class="invalid-feedback">
                    ${dateError}
                </div>
            </#if>
        </div>
        <div class="form-floating">
            <input
                    type="number"
                    min="1"
                    step="1"
                    class="form-control no-border-radius <#if daysError??>is-invalid</#if>"
                    id="days"
                    name="days"
                    <#if houseFilter?? && houseFilter.days??>value="${houseFilter.days}"</#if>
                    placeholder="На скільки днів?"
            />
            <label class="text-dark" for="days">На скільки днів?</label>
            <#if daysError??>
                <div class="invalid-feedback">
                    ${daysError}
                </div>
            </#if>
        </div>
        <div class="form-floating">
            <input
                    type="number"
                    min="1"
                    step="1"
                    class="form-control no-border-radius <#if peopleError??>is-invalid</#if>"
                    id="people"
                    name="people"
                    <#if houseFilter?? && houseFilter.people??>value="${houseFilter.people}"</#if>
                    placeholder="На скільки людей?"
            />
            <label class="text-dark" for="people">На скільки людей?</label>
            <#if peopleError??>
                <div class="invalid-feedback">
                    ${peopleError}
                </div>
            </#if>
        </div>
        <div>
            <button class="fs-4 btn btn-warning" type="submit"
                    style="border-radius: 0 0.25rem 0.25rem 0; height: 58px">Пошук</button>
        </div>
    </form>
</main>

    </@hs.headStyle>
