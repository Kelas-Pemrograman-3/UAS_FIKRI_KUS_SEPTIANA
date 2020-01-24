<template>
  <q-page>
<div
class="judul text-h6 q-pa-md bg-grey-2 q-mb-md">
        <q-toolbar-title >
          Please input the financial data
        </q-toolbar-title>
<div class="komentar absolute-top-right q-pt-md q-pr-md">
<q-icon name="money" />
  Finance
</div>
</div>
    <div class="row justify-center q-pt-md">
      <div class="col-md-5 col-xs-8">
        <q-card>
          <q-card-section>
            <div class="text-h5 q-pb-md text-center">
              <b>Input Financial</b>
            </div>
            <q-form @submit="onSubmit" @reset="onReset" class="q-gutter-sm">

              <q-input
                filled
                v-model="namauang"
                label="Full Name *"
                lazy-rules
                :rules="[ val => val !== null && val !== '' || 'Please type Your Name']"
              />

              <q-input
                filled
                mask="##"
                v-model="semesteruang"
                label="Semester *"
                lazy-rules
                :rules="[ val => val && val.length > 0 || 'Please type Your Semester']"
              />

              <q-input
                filled
                type="number"
                v-model="sppuang"
                label="Nominal SPP *"
                lazy-rules
                :rules="[ val => val !== '' && val !== null || 'Please type Your Nominal']"
              />

              <div>
                <q-btn label="Add +" type="submit" color="light-green-14" class="q-mt-md" />
                <q-btn label="Reset" type="reset" color="indigo-9" flat class="q-ml-sm q-mt-md" />
              </div>
            </q-form>
          </q-card-section>
        </q-card>
      </div>
    </div>
  </q-page>
</template>

<script>
export default {
  data () {
    return {
      namauang: '',
      semesteruang: '',
      sppuang: ''
    }
  },
  methods: {
    onSubmit () {
      this.$axios.post('keuangan/postuang', {
        namauang: this.namauang,
        semesteruang: this.semesteruang,
        sppuang: this.sppuang
      }).then(res => {
        if (res.data.error) {
          this.showNotif(res.data.pesan, 'negative')
        } else {
          this.showNotif(res.data.pesan, 'Positive')
          this.onReset()
        }
      })
    },
    onReset () {
      this.namauang = ''
      this.semesteruang = ''
      this.sppuang = ''
    },
    showNotif (msg, color) {
      this.$q.notify({
        message: msg,
        color: color
      })
    }
  }
}
</script>
