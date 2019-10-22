/**
 * Created by JimBarrows on 10/18/19.
 */

import {Then} from 'cucumber'

Then('the operation was successful', function () {
	expect(this.result.error, JSON.stringify(this.result.error)).to.be.null
})
