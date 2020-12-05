require 'functions'

local function find()
    local ids = parseBoardingPasses()

    for id = ids[1], ids[#ids] do
        if not ids:has(id) and ids:has(id - 1) and ids:has(id + 1) then
            return id
        end
    end

    error('404')
end

print(find())
