Feature: Interval algorithm

#  Scenario: Picks only available interval
#    Given there is a meeting scheduling problem
#    And this meeting has to happen in period '2017-10-02T14:00Z/2017-10-06T16:00Z'
#    And there is a participant
#    And this participant declares availability '2017-10-02T14:00Z/2017-10-06T16:00Z'
#    When the meeting was scheduled
#    Then the meeting scheduled is in period '2017-10-02T14:00Z/2017-10-06T16:00Z'

  Scenario: Picks longest available interval
    Given there is a meeting scheduling problem
    And this meeting has to happen in period '2017-10-01T00:00Z/2017-10-30T16:00Z'
    And there is a participant
    And this participant declares availability '2017-10-02T09:00Z/2017-10-02T10:00Z'
    And this participant declares availability '2017-10-03T12:00Z/2017-10-03T15:00Z'
    And this participant declares availability '2017-10-04T18:00Z/2017-10-04T19:00Z'
    When the meeting was scheduled
    Then the meeting scheduled is in period '2017-10-03T12:00Z/2017-10-03T15:00Z'

#  Scenario: Picks longest interval two people are available in
#    Given there is a meeting scheduling problem
#    And this meeting has to happen in period '2017-10-01T00:00Z/2017-10-30T16:00Z'
#    And there is a participant
#    And this participant declares availability '2017-10-02T09:00Z/2017-10-02T13:00Z'
#    And there is a participant
#    And this participant declares availability '2017-10-02T10:00Z/2017-10-02T12:00Z'
#    When the meeting was scheduled
#    Then the meeting scheduled is in period '2017-10-02T10:00Z/2017-10-02T12:00Z'
#
#  Scenario: Picks interval the required person is available in
#    Given there is a meeting scheduling problem
#    And this meeting has to happen in period '2017-10-01T00:00Z/2017-10-30T16:00Z'
#    And there is a participant 'optional' with importance '0'
#    And this participant declares availability '2017-10-02T09:00Z/2017-10-02T13:00Z'
#    And there is a participant 'required' with importance '1'
#    And this participant declares availability '2017-10-03T13:00Z/2017-10-03T19:00Z'
#    When the meeting was scheduled
#    Then the meeting scheduled is in period '2017-10-03T13:00Z/2017-10-03T19:00Z'