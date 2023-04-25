<#import '../macros/head_footer.ftlh' as h>
<#import '../macros/pager.ftlh' as p>

<@h.head>


<div class="album py-5 bg-dark">
    <div class="container">
        <div
                class="row row-cols-1 row-cols-sm-1 row-cols-md-2 row-cols-lg-2 row-cols-xl-3 g-3"
        >
            <#list page.content as wish>
                <div class="col">

                    <div class="card shadow-sm h-100">
                        <img
                                src="<#if wish.house.image?? >/images/${wish.house.image.getId()}</#if>"
                                alt="No photo"
                                class="bd-placeholder-img card-img-top"
                                style="background-size: cover; width: 100%; height: 320px"
                        />

                        <div class="card-body d-flex flex-column">
                            <p class="card-text fw-bold h3"><#if wish.house.name??>${wish.house.name} <#else>Назва</#if></p>

                            <p class="card-text text-break fs-7">Локація<br>
                                <#if wish.house.address.country??>${wish.house.address.country},</#if>
                                <#if wish.house.address.city??>${wish.house.address.city},</#if>
                                <#if wish.house.address.street?has_content>вул.${wish.house.address.street},</#if>
                                <#if wish.house.address.number?has_content>буд.${wish.house.address.number},</#if>
                                <#if wish.house.address.apartment?has_content>кв.${wish.house.address.apartment}.</#if>
                            </p>

                            <p class="card-text text-break fs-7">
                                Опис<br><#if wish.house.description??>${wish.house.description}</#if></p>


                            <form class="mt-auto productForm" action="">
                                <div class="btn-group sizes w-100">
                                    <input
                                            class="btn-check"
                                            type="radio"
                                            id="scoreWord"
                                            checked
                                            disabled
                                    />
                                    <label class="btn btn-outline-secondary" for="scoreWord">
                                        Середня оцінка
                                    </label>

                                    <input
                                            class="btn-check"
                                            type="radio"
                                            id="score"
                                            checked
                                            disabled
                                    />
                                    <label class="btn btn-outline-secondary" for="score">
                                        <#if wish.house.getAverRating() == -1>поки оцінок немає<#else>${wish.house.getAverRating()}</#if>
                                    </label>
                                </div>
                                <div class="d-flex flex-column ">
                                    <a href="/houses/${wish.house.id}/detail"
                                    >
                                        <#if wish.house.getCountFeedback() == 0>ваш відгук буде перший
                                        <#else>${wish.house.getCountFeedback()} відгуків</#if>
                                    </a>
                                </div>

                                <span class="d-flex align-items-center justify-content-between mt-2">

                    <span
                            class="fw-bold fs-4 priceDisc product text-warning"
                            style="flex-grow: 2"
                    >
                        Вартість за добу
                    </span>
                    <span
                            class="fw-bold fs-4 price product"
                            style="flex-grow: 2"
                    >
                        <#if wish.house.price??>${wish.house.price} <#else>невідомо</#if> грн
                    </span>

                  </span>
                            </form>


                        </div>

                        <div class="d-flex flex-column btn-group text-center">

                            <a href="/houses/${wish.house.id}/detail"
                               class="btn fw-bold btn-sm btn-warning mb-3 mx-5 py-2 rounded"
                            >
                                Деталі
                            </a>
                            <a href="/wishes/${wish.house.id}/delete"
                               class="btn fw-bold btn-sm btn-danger mb-3 mx-5 py-2 rounded"
                            >
                                Видалити зі списку
                            </a>

                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>

    <div class="d-flex mt-5 justify-content-center" >
        <@p.pager url page/>
    </div>

</div>

</@h.head>