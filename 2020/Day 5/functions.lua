function parseBoardingPasses()
    local ids = {}

    for line in io.lines('boarding-passes.txt') do
        ids[#ids + 1] = tonumber(line:gsub('F', '0'):gsub('L', '0'):gsub('B', '1'):gsub('R', 1), 2)
    end

    table.sort(ids)

    return ids
end

function has(ids, id)
    for _, value in ipairs(ids) do
        if value == id then
            return true
        end
    end

    return false
end
