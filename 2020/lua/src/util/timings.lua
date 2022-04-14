local ns = 1 / 1000000000
local us = 1 / 1000000
local ms = 1 / 1000
local s = 1

local function formatTiming(time, suffix)
    return ("%.2f %s"):format(time, suffix)
end

local function handleTiming(time)
    if time < us then
        return formatTiming(time / ns, 'ns')
    elseif time < ms then
        return formatTiming(time / us, 'us')
    elseif time < s then
        return formatTiming(time / ms, 'ms')
    else
        return formatTiming(time, ' s')
    end
end

function handleTimings(days)
    for nr = 1, #days do
        local day = days[nr]

        for _, a in pairs({ 'timeTotal', 'timeSetup', 'time1', 'time2' }) do
            day[a] = handleTiming(day[a])
        end
    end
end
