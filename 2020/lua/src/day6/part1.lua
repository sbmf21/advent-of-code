
function part1()
    local groups = parseGroups()

    for key, group in ipairs(groups) do
        local chars = {}

        for _, line in ipairs(group) do
            for ci = 1, #line do
                local char = line:sub(ci, ci)

                if not table_has(chars, char) then
                    chars[#chars + 1] = char
                end
            end
        end

        groups[key] = #chars
    end

    return table_sum(groups)
end
