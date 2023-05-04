<#import '../macros/head_footer.ftlh' as h>
<#import '../macros/pager.ftlh' as p>

<@h.head>


<div class="album py-5 bg-dark ">
    <div class="container">
        <div
                class="row row-cols-1 row-cols-sm-1 row-cols-md-2 row-cols-lg-2 row-cols-xl-3 g-3"
        >
            <#list page.content as house>
                <div class="col">

                    <div class="card shadow-sm h-100">
                        <img
                                src="<#if house.image?? >/images/${house.image.getId()}</#if>"
                                alt="No photo"
                                class="bd-placeholder-img card-img-top"
                                style="background-size: cover; width: 100%; height: 320px"
                        />

                        <div class="card-body d-flex flex-column">
                            <p class="card-text fw-bold h3"><#if house.name??>${house.name} <#else>Назва</#if></p>

                            <p class="card-text text-break fs-7">Локація<br>
                                <#if house.address.country??>${house.address.country},</#if>
                                <#if house.address.city??>${house.address.city},</#if>
                                <#if house.address.street?has_content>вул.${house.address.street},</#if>
                                <#if house.address.number?has_content>буд.${house.address.number},</#if>
                                <#if house.address.apartment?has_content>кв.${house.address.apartment}.</#if>
                            </p>

                            <p class="card-text text-break fs-7">
                                Опис<br><#if house.description??>${house.description}</#if></p>


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
                                        <#if house.getAverRating() == -1>поки оцінок немає<#else>${house.getAverRating()}</#if>
                                    </label>
                                </div>
                                <div class="d-flex flex-column ">
                                    <a href="/houses/${house.id}/detail"
                                    >
                                        <#if house.getCountFeedback() == 0>ваш відгук буде перший
                                        <#else>${house.getCountFeedback()} відгуків</#if>
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
                        <#if house.price??>${house.price} <#else>невідомо</#if> грн
                    </span>

                  </span>
                            </form>


                        </div>

                        <div class="d-flex flex-column btn-group text-center">

                            <a href="/houses/${house.id}/detail"
                                    class="btn fw-bold btn-sm btn-success mb-3 mx-5 py-2 rounded"
                            >
                                Деталі
                            </a
                            >
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>
    <div class="d-flex mt-5 justify-content-center" >
        <ul class="pagination ">

            <li class="page-item disable ">
                <#if page.number == 0>
                    <a class="page-link" href="#">Попередня</a>
                <#else >
                    <form action="${url}page=${page.number - 1}" method="post">
                        <input class="page-link"  type="submit" value="Попередня" />
                    </form>
                </#if>
            </li>

            <li class="page-item disable">
                <a class="page-link" href="#">${page.number+1}</a>
            </li>
            <li class="page-item disable">
                <a class="page-link" href="#">/</a>
            </li>
            <li class="page-item disable">
                <a class="page-link" href="#">${page.getTotalPages()}</a>
            </li>

            <li class="page-item disable">
                <#if page.number+1 == page.totalPages>
                    <a class="page-link" href="#">Наступна</a>
                <#else >
                    <form action="${url}page=${page.number + 1}" method="post">
                        <input class="page-link"  type="submit" value="Наступна" />
                    </form>
                </#if>
            </li>
        </ul>
    </div>
</div>


</@h.head>