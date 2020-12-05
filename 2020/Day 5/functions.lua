local cache = {
    rows = {},
    columns = {}
}

local function loadBoardingPasses() return io.lines('boarding-passes.txt') end
local function extractRow(pass) return pass:sub(1, 7) end
local function extractColumn(pass) return pass:sub(8, 10) end

local function isValidRow(pass)
    local row = extractRow(pass)

    for i = 1, #row do
        if not row:sub(i, i):match('[FB]') then
            return false
        end
    end

    return true
end

local function isValidColumn(pass)
    local column = extractColumn(pass)

    for i = 1, #column do
        if not column:sub(i, i):match('[RL]') then
            return false
        end
    end

    return true
end

local function isValidPass(pass)
    if #pass ~= 10 then
        return false
    end

    return isValidRow(pass)
       and isValidColumn(pass)
end

local function calculateDifference(min, max)
    return math.floor(math.abs(max - min) / 2 + 0.5)
end

local function convertRow(line)
    local row = extractRow(line)

    if(cache.rows[row] ~= nil) then
        return cache.rows[row]
    end

    local min = 0; local max = 127

    for i = 1, #row do
        local c = row:sub(i, i)

        if c == 'F' then
            max = max - calculateDifference(min, max)
        else
            min = min + calculateDifference(min, max)
        end
    end
    
    if min ~= max then
        error(min .. ' and ' .. max .. ' did not match for pass ' .. row)
    end

    cache.rows[row] = min

    return min
end

local function convertColumn(line)
    local column = extractColumn(line)

    if(cache.columns[column] ~= nil) then
        return cache.columns[column]
    end

    local min = 0; local max = 7

    for i = 1, #column do
        local c = column:sub(i, i)

        if c == 'L' then
            max = max - calculateDifference(min, max)
        else
            min = min + calculateDifference(min, max)
        end
    end

    cache.columns[column] = min

    return min
end

function calculateId(row, column)
    return row * 8 + column
end

local function convertPass(line)
    local pass = {
        row = convertRow(line),
        column = convertColumn(line)
    }

    pass.id = calculateId(pass.row, pass.column)

    return pass
end

function parseBoardingPasses()
    local input = loadBoardingPasses()
    local passes = {}

    for pass in input do
        if isValidPass(pass) then
            passes[#passes + 1] = convertPass(pass)
        else
            print('Invalid pass: ' .. pass)
        end
    end

    return passes
end

function parseBoardingPassesToIndices()
    local indices = {}

    for _, pass in ipairs(parseBoardingPasses()) do
        if indices[pass.row] == nil then
            indices[pass.row] = {}
        end

        indices[pass.row][pass.column] = pass
    end

    return indices
end
