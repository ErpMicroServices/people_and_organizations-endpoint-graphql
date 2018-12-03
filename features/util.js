/**
 * Created by JimBarrows on 2018-12-02.
 */
export function convert_to_table_name(type) {
	return type.replace(/\s+/g, '_').toLowerCase()
}
