require 'functions'

local function findId()
    local index = parseBoardingPassesToIndices()

    -- rows start at 0, end at 127; not first; not last
    for row = 1, 126 do
        -- columns start at 0, end at 7, id - 1 and id + 1 must exist so 0 and 7 cant be
        for column = 1, 6 do
            if index[row] ~= nil and index[row][column - 1] ~= nil and index[row][column] == nil and index[row][column + 1] ~= nil then
                return calculateId(row, column)
            end
        end
    end
    
    error('404')
end

print(findId())