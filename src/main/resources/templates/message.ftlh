<#import "macros/head_footer_style.ftlh" as hs>
<@hs.headStyle>


<main class="px-3 text-center text-white bg-dark d-flex align-items-center justify-content-center flex-column view">

    <div class="d-flex flex-column justify-content-center align-items-center border rounded border-rounded border-white" style="height: 250px; width: 400px" >

        <h2><#if message??>${message}</#if></h2>
        <#if messageUrl??>
<#--            <form action="${messageUrl}" method="post">-->
<#--                <input type="submit" class="btn fw-bold btn-sm btn-success mb-3 mx-5 py-2 rounded" value="OK" />-->
<#--            </form>-->
            <a href="${messageUrl}" class="btn fw-bold btn-sm btn-success mb-3 mx-5 py-2 rounded">Ok</a>
            <#else>
        <a href="/" class="btn fw-bold btn-sm btn-success mb-3 mx-5 py-2 rounded"> На головну </a>
        </#if>

    </div>


</main>

</@hs.headStyle>