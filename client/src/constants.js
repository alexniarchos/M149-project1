const eventTypes = [
  {
    value: 'ABANDONED_VEHICLES',
    text: 'Abandoned vehicles'
  },
  {
    value: 'ALLEY_LIGHT_OUT',
    text: 'Alley light out'
  },
  {
    value: 'GARBAGE_CART',
    text: 'Garbage cart'
  },
  {
    value: 'GRAFFITI_REMOVAL',
    text: 'Graffiti removal'
  },
  {
    value: 'POTHOLE_IN_STREET',
    text: 'Pothole in street'
  },
  {
    value: 'RODENT_BAITING',
    text: 'Rodent baiting'
  },
  {
    value: 'SANITATION_VIOLATION',
    text: 'Sanitation violation'
  },
  {
    value: 'STREET_LIGHTS_ALL_OUT',
    text: 'Street lights all out'
  },
  {
    value: 'STREET_LIGHT_OUT',
    text: 'Street light out'
  },
  {
    value: 'TREE_DEBRIS',
    text: 'Tree debris'
  },
  {
    value: 'TREE_TRIM',
    text: 'Tree trim'
  }
];

const eventStatusTypes = [
  {
    value: 'COMPLETED',
    text: 'Completed'
  },
  {
    value: 'COMPLETED_DUPLICATE',
    text: 'Completed duplicate'
  },
  {
    value: 'OPEN',
    text: 'Open'
  },
  {
    value: 'OPEN_DUPLICATE',
    text: 'Open duplicate'
  }
];

const treeLocationTypes = [
  {
    value: 'ALLEY',
    text: 'Alley'
  },
  {
    value: 'PARKWAY',
    text: 'Parkway'
  },
  {
    value: 'VACANT_LOT',
    text: 'Vacant lot'
  }
];

const fieldTypes = {
  status: 'EventStatus',
  completionDate: 'Date',
  serviceRequestNumber: 'String',
  type: 'EventType',
  address: 'String',
  zipCode: 'Number',
  customCoordinateX: 'Number',
  customCoordinateY: 'Number',
  ward: 'Number',
  policeDistrict: 'Number',
  communityArea: 'Number',
  latitude: 'Number',
  longitude: 'Number',
  historicalWards: 'Number',
  zipCodes: 'Number',
  communityAreas: 'Number',
  cencusTracts: 'Number',
  wards: 'Number',
  ssa: 'Number',
  licencePlate: 'String',
  model: 'String',
  color: 'String',
  daysStationed: 'Number',
  numberDelivered: 'Number',
  typeOfSurface: 'String',
  graffitiLocation: 'String',
  treeLocation: 'TreeLocationEnum',
  numberFilled: 'Number',
  numberOfPremisesBaited: 'Number',
  numberOfPremisesWithGarbage: 'Number',
  numberOfPremisesWithRats: 'Number',
  typeOfViolation: 'String',
  currentActivity: 'String',
  mostRecentAction: 'String'
};

const fieldTypesOrdered = [
  'status',
  'completionDate',
  'serviceRequestNumber',
  'type',
  'address',
  'zipCode',
  'customCoordinateX',
  'customCoordinateY',
  'ward',
  'policeDistrict',
  'communityArea',
  'latitude',
  'longitude',
  'historicalWards',
  'zipCodes',
  'communityAreas',
  'cencusTracts',
  'wards',
  'ssa',
  'licencePlate',
  'model',
  'color',
  'daysStationed',
  'numberDelivered',
  'typeOfSurface',
  'graffitiLocation',
  'treeLocation',
  'numberFilled',
  'numberOfPremisesBaited',
  'numberOfPremisesWithGarbage',
  'numberOfPremisesWithRats',
  'typeOfViolation',
  'currentActivity',
  'mostRecentAction'
]

export {
  eventTypes,
  eventStatusTypes,
  treeLocationTypes,
  fieldTypes,
  fieldTypesOrdered
};
