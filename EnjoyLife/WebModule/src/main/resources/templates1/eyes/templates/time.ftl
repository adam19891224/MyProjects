<ul id="time-year" class="time-year">
[#if times?? && times?size > 0]
    [#list times as time]
        [#--判断是否是第一个年份--]
        [#if time_index == 0]
            <li>
                <h3>
                    ${time.date}<i class="up"></i>
                </h3>
                <ul class="show-ul" is-show="true" data-Y="${time.date}">
                    [#if time.list?? && time.list?size > 0]
                        [#list time.list as list]
                            [#--判断是否是第一个月份--]
                            [#if list_index == 0]
                                <li>
                                    <h5 data-M="${list.date}">
                                        [#--第一个月份表示当前展示的是这个月份，则让下游标显示--]
                                        ${list.date}<i class="show"></i>
                                    </h5>
                                </li>
                            [#else]
                                <li>
                                    <h5 data-M="${list.date}">
                                        ${list.date}<i></i>
                                    </h5>
                                </li>
                            [/#if]
                        [/#list]
                    [/#if]
                </ul>
            </li>
        [#else]
            <li>
                <h3>
                    ${time.date}<i class="down"></i>
                </h3>
                <ul is-show="false" data-Y="${time.date}">
                    [#if time.list?? && time.list?size > 0]
                        [#list time.list as list]
                            <li>
                                <h5 data-M="${list.date}">
                                    ${list.date}<i></i>
                                </h5>
                            </li>
                        [/#list]
                    [/#if]
                </ul>
            </li>
        [/#if]
    [/#list]
[/#if]
</ul>