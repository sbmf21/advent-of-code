require 'functions'

local function find()
    local ids = parseBoardingPasses()

    for id = ids[1], ids[#ids] do
        if not has(ids, id) and has(ids, id - 1) and has(ids, id + 1) then
            return id
        end
    end

    error('404')
end

print(find())
