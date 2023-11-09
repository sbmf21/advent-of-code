function part2()
    local groups = parseGroups()

    for key, group in ipairs(groups) do
        local chars = {}

        for ci = 1, #group[1] do
            local char = group[1]:sub(ci, ci)

            if not table_has(chars, char) then
                chars[#chars + 1] = char
            end
        end

        for li = 2, #group do
            local newChars = {}
            for _, c in ipairs(chars) do
                if group[li]:find(c) then
                    newChars[#newChars + 1] = c
                end
            end
            chars = newChars
        end

        groups[key] = #chars
    end

    return table_sum(groups)
end
