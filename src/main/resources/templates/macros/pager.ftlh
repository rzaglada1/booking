<#macro pager url page>


        <ul class="pagination ">

            <li class="page-item disable ">
                <#if page.number == 0>
                <a class="page-link" href="#">Попередня</a>
                <#else >
                <a class="page-link" href="${url}?page=${page.number - 1}">Попередня</a>
                </#if>
            </li>

            <li class="page-item disable">
                <a class="page-link" href="#">${page.number+1}</a>
            </li>
            <li class="page-item disable">
                <a class="page-link" href="#">/</a>
            </li>
            <li class="page-item disable">
                <a class="page-link" href="#">${page.totalPages}</a>
            </li>

            <li class="page-item disable">
                <#if page.number+1 == page.totalPages>
                <a class="page-link" href="#">Наступна</a>
                <#else >
                <a class="page-link" href="${url}?page=${page.number + 1}">Наступна</a>
                </#if>
            </li>
        </ul>

</#macro>