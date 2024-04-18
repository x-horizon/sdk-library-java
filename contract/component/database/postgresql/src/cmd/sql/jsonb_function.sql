CREATE OR REPLACE FUNCTION jsonb_list_object_key_id_in(jsonb_list_object_contain_key_id JSONB, ids BIGINT[])
    RETURNS BOOLEAN AS
$$
DECLARE
    item JSONB;
BEGIN
    FOR item IN SELECT JSONB_ARRAY_ELEMENTS(jsonb_list_object_contain_key_id)
        LOOP
            IF (item ->> 'id')::BIGINT = ANY (ids) THEN
                RETURN TRUE;
            END IF;
        END LOOP;
    RETURN FALSE;
END;
$$ LANGUAGE plpgsql IMMUTABLE;


CREATE OR REPLACE FUNCTION jsonb_list_object_key_id_equal(jsonb_list_object_contain_key_id JSONB, id BIGINT)
    RETURNS BOOLEAN AS
$$
DECLARE
    item JSONB;
BEGIN
    FOR item IN SELECT JSONB_ARRAY_ELEMENTS(jsonb_list_object_contain_key_id)
        LOOP
            IF (item ->> 'id')::BIGINT = id THEN
                RETURN TRUE;
            END IF;
        END LOOP;
    RETURN FALSE;
END;
$$ LANGUAGE plpgsql IMMUTABLE;


CREATE OR REPLACE FUNCTION jsonb_list_object_key_type_in(jsonb_list_object_contain_key_type JSONB, types BIGINT[])
    RETURNS BOOLEAN AS
$$
DECLARE
    item JSONB;
BEGIN
    FOR item IN SELECT JSONB_ARRAY_ELEMENTS(jsonb_list_object_contain_key_type)
        LOOP
            IF (item ->> 'type')::BIGINT = ANY (types) THEN
                RETURN TRUE;
            END IF;
        END LOOP;
    RETURN FALSE;
END;
$$ LANGUAGE plpgsql IMMUTABLE;


CREATE OR REPLACE FUNCTION jsonb_list_object_key_type_equal(jsonb_list_object_contain_key_id JSONB, type BIGINT)
    RETURNS BOOLEAN AS
$$
DECLARE
    item JSONB;
BEGIN
    FOR item IN SELECT JSONB_ARRAY_ELEMENTS(jsonb_list_object_contain_key_id)
        LOOP
            IF (item ->> 'type')::BIGINT = type THEN
                RETURN TRUE;
            END IF;
        END LOOP;
    RETURN FALSE;
END;
$$ LANGUAGE plpgsql IMMUTABLE;


CREATE OR REPLACE FUNCTION jsonb_list_number_in(jsonb_list_number JSONB, numbers BIGINT[])
    RETURNS BOOLEAN AS
$$
DECLARE
    item JSONB;
BEGIN
    FOR item IN SELECT JSONB_ARRAY_ELEMENTS(jsonb_list_number)
        LOOP
            IF item::BIGINT = ANY (numbers) THEN
                RETURN TRUE;
            END IF;
        END LOOP;
    RETURN FALSE;
END;
$$ LANGUAGE plpgsql IMMUTABLE;


CREATE OR REPLACE FUNCTION jsonb_list_number_equal(jsonb_list_number JSONB, number BIGINT)
    RETURNS BOOLEAN AS
$$
DECLARE
    item JSONB;
BEGIN
    FOR item IN SELECT JSONB_ARRAY_ELEMENTS(jsonb_list_number)
        LOOP
            IF item::BIGINT = number THEN
                RETURN TRUE;
            END IF;
        END LOOP;
    RETURN FALSE;
END;
$$ LANGUAGE plpgsql IMMUTABLE;


CREATE OR REPLACE FUNCTION jsonb_list_string_equal(jsonb_list_string JSONB, string VARCHAR)
    RETURNS BOOLEAN AS
$$
DECLARE
    item JSONB;
BEGIN
    FOR item IN SELECT JSONB_ARRAY_ELEMENTS(jsonb_list_string)
        LOOP
            IF item::VARCHAR = string THEN
                RETURN TRUE;
            END IF;
        END LOOP;
    RETURN FALSE;
END;
$$ LANGUAGE plpgsql IMMUTABLE;


CREATE OR REPLACE FUNCTION jsonb_list_string_like(jsonb_list_string JSONB, string VARCHAR)
    RETURNS BOOLEAN AS
$$
DECLARE
    item JSONB;
BEGIN
    FOR item IN SELECT JSONB_ARRAY_ELEMENTS(jsonb_list_string)
        LOOP
            IF POSITION(string IN item::VARCHAR) > 0 THEN
                RETURN TRUE;
            END IF;
        END LOOP;
    RETURN FALSE;
END;
$$ LANGUAGE plpgsql IMMUTABLE;